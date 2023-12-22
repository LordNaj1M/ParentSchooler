import torch
import numpy as np
import pandas as pd
from datasets import Dataset, load_dataset, DatasetDict
from pandas import DataFrame as df
from transformers import BertTokenizer, EncoderDecoderModel
from transformers import AutoModelForMaskedLM, AutoTokenizer, Trainer, TrainingArguments

class MLMTrainer:
    def __init__(self, data_path, model_name="cahya/bert2gpt-indonesian-summarization"):
        """
        Initializes the MLMTrainer class.

        Args:
            data_path (str): Path to the CSV file containing the dataset.
            model_name (str): Name of the pre-trained model to use.
        """
        self.base_data = pd.read_csv(data_path)
        self.base_data = self.base_data[['abstract']]

        self.tokenizer = BertTokenizer.from_pretrained(model_name)
        self.tokenizer.bos_token = self.tokenizer.cls_token
        self.tokenizer.eos_token = self.tokenizer.sep_token
        self.model = EncoderDecoderModel.from_pretrained(model_name)

    def mlm_tensor_dataset(self, data):
        """
        Tokenizes and creates MLM examples for a given dataset.

        Args:
            data (pd.DataFrame): Input dataset.

        Returns:
            dict: Dictionary containing input_ids, token_type_ids, attention_mask, and labels.
        """
        inputs = self.tokenizer(data['abstract'], return_tensors="pt", max_length=512, truncation=True, padding="max_length")

        rand = torch.rand(inputs.input_ids.shape)
        mask_arr = (rand < 0.15) * (inputs.input_ids != self.tokenizer.pad_token_id)

        selection = []
        for i in range(inputs.input_ids.shape[0]):
            selection.append(torch.flatten(mask_arr[i].nonzero()).tolist())

        for i in range(inputs.input_ids.shape[0]):
            inputs.input_ids[i, selection[i]] = self.tokenizer.mask_token_id

        labels = inputs.input_ids.clone()
        labels[mask_arr] = -100

        return {
            'input_ids': inputs.input_ids,
            'token_type_ids': inputs.token_type_ids,
            'attention_mask': inputs.attention_mask,
            'labels': labels
        }

    def prepare_dataset(self, split_percentage=0.9):
        """
        Prepares the MLM dataset by tokenizing and splitting into train and validation sets.

        Args:
            split_percentage (float): Percentage of data for training (default is 0.9).

        Returns:
            DatasetDict: Dictionary containing 'train' and 'validation' datasets.
        """
        mlm_dataset = self.base_data.map(self.mlm_tensor_dataset, batched=True)

        train_size = int(len(mlm_dataset) * split_percentage)

        mlm_dataset_dict = DatasetDict({
            'train': mlm_dataset.select(list(range(train_size))),
            'validation': mlm_dataset.select(list(range(train_size, len(mlm_dataset))))
        })

        return mlm_dataset_dict

    def train_model(self, mlm_dataset_dict, output_dir='./results', num_train_epochs=3, per_device_train_batch_size=16,
                    save_steps=10_000, save_total_limit=2):
        """
        Fine-tunes the model using the provided MLM dataset.

        Args:
            mlm_dataset_dict (DatasetDict): Dictionary containing 'train' and 'validation' datasets.
            output_dir (str): Directory to save training results.
            num_train_epochs (int): Number of training epochs.
            per_device_train_batch_size (int): Batch size per device.
            save_steps (int): Save model every specified number of steps.
            save_total_limit (int): Limit the total number of checkpoints to save.

        Returns:
            Trainer: Trained Trainer object.
        """
        training_args = TrainingArguments(
            output_dir=output_dir,
            overwrite_output_dir=True,
            num_train_epochs=num_train_epochs,
            per_device_train_batch_size=per_device_train_batch_size,
            save_steps=save_steps,
            save_total_limit=save_total_limit,
        )

        trainer = Trainer(
            model=self.model,
            args=training_args,
            train_dataset=mlm_dataset_dict['train'],
            eval_dataset=mlm_dataset_dict['validation'],
        )

        trainer.train()

        return trainer

    def save_model(self, trainer, output_dir="./fine_tuned_model"):
        """
        Saves the fine-tuned model.

        Args:
            trainer (Trainer): Trained Trainer object.
            output_dir (str): Directory to save the fine-tuned model.
        """
        trainer.save_model(output_dir)


data_path = "complete_journal_data_new.csv"
mlm_trainer = MLMTrainer(data_path)
mlm_dataset_dict = mlm_trainer.prepare_dataset()
trained_trainer = mlm_trainer.train_model(mlm_dataset_dict)
mlm_trainer.save_model(trained_trainer)
