from transformers import EncoderDecoderModel, EncoderDecoderConfig
import torch

class EncoderDecoderModelConverter:
    def __init__(self, model_folder_path="./fine_tuned_model"):
        """
        Initializes the EncoderDecoderModelConverter class.

        Args:
            model_folder_path (str): Path to the folder containing the fine-tuned model files.
        """
        self.model_folder_path = model_folder_path

    def load_model(self):
        """
        Loads the encoder-decoder model and configuration.

        Returns:
            EncoderDecoderModel: Loaded encoder-decoder model.
        """
        config = EncoderDecoderConfig.from_pretrained(self.model_folder_path)
        encoder_decoder_model = EncoderDecoderModel.from_pretrained(self.model_folder_path, config=config)
        return encoder_decoder_model

    def save_to_hdf5(self, encoder_decoder_model, h5_model_path="./encoder_decoder_model.h5"):
        """
        Saves the encoder and decoder state dictionaries to an HDF5 file.

        Args:
            encoder_decoder_model (EncoderDecoderModel): Loaded encoder-decoder model.
            h5_model_path (str): Path to save the HDF5 file.
        """
        encoder_state_dict = encoder_decoder_model.encoder.state_dict()
        decoder_state_dict = encoder_decoder_model.decoder.state_dict()

        torch.save({
            'encoder_state_dict': encoder_state_dict,
            'decoder_state_dict': decoder_state_dict,
        }, h5_model_path)

converter = EncoderDecoderModelConverter()
loaded_model = converter.load_model()
converter.save_to_hdf5(loaded_model)
