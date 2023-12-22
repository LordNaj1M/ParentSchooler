from transformers import EncoderDecoderModel, EncoderDecoderConfig, BertTokenizer
import torch

def clean_sentence(sentence):
    """
    Cleans a sentence by capitalizing and formatting each sentence in a text.

    Args:
        sentence (str): Input sentence or text.

    Returns:
        str: Cleaned and formatted sentence.
    """
    split_sentence = sentence.split('. ')

    for i in range(len(split_sentence)):
        split_sentence[i] = split_sentence[i].strip().capitalize()

    final_sentence = '. '.join(split_sentence)

    return final_sentence

class Summarizer:
    def __init__(self, h5_model_path="./encoder_decoder_model.h5"):
        """
        Initializes the Summarizer class.

        Args:
            h5_model_path (str): Path to the HDF5 model file.
        """
        # Load the state dictionaries from the HDF5 file
        checkpoint = torch.load(h5_model_path)

        # Create a new instance of the encoder-decoder model with the same configuration
        config = EncoderDecoderConfig.from_pretrained('./fine_tuned_model')  # Replace with your desired configuration
        self.model = EncoderDecoderModel(config=config)

        # Load the state dictionaries into the model's encoder and decoder
        self.model.encoder.load_state_dict(checkpoint['encoder_state_dict'])
        self.model.decoder.load_state_dict(checkpoint['decoder_state_dict'])

        # Initialize the tokenizer
        self.tokenizer = BertTokenizer.from_pretrained("cahya/bert2gpt-indonesian-summarization")

    def generate_summary(self, input_text):
        """
        Generates a summary for the given input text using the loaded model.

        Args:
            input_text (str): Input text to be summarized.

        Returns:
            str: Generated summary.
        """
        # Tokenize the input text
        input_ids = self.tokenizer.encode(input_text, return_tensors="pt", max_length=512, truncation=True)

        # Generate a summary
        with torch.no_grad():
            summary_ids = self.model.generate(
                input_ids, max_length=999, min_length=30, num_beams=10, length_penalty=2.0, early_stopping=True
            )

        # Decode the generated summary tokens back to text
        summary_text = self.tokenizer.decode(summary_ids[0], skip_special_tokens=True)

        # Clean and format the generated summary
        cleaned_summary = clean_sentence(summary_text)

        return cleaned_summary

summarizer = Summarizer()
input_text = "..."  
generated_summary = summarizer.generate_summary(input_text)
print("Generated Summary: \n", generated_summary)
