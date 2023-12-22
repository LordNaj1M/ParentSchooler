# MLM Fine-Tuning and Summarization with Encoder-Decoder Model

This repository contains code for fine-tuning a BERT-based Masked Language Model (MLM) and generating summaries using an Encoder-Decoder model.

## MLM Fine-Tuning

### Requirements

- Python 3.x
- PyTorch
- Transformers library
- pandas
- datasets library

### Fine-Tuning Steps

1. Install the required libraries:

    ```
    pip install torch pandas datasets transformers
    ```

2. Run the fine-tuning script:

    ```
    python mlm_fine_tuning.py
    ```

3. The script will prepare the MLM dataset, train the model, and save the fine-tuned model.

## Encoder-Decoder Model Conversion

### Requirements

- Python 3.x
- PyTorch
- Transformers library

### Conversion Steps

1. Install the required libraries:

    ```
    pip install torch transformers
    ```

2. Run the conversion script:

    ```
    python encoder_decoder_conversion.py
    ```

3. The script will load the fine-tuned model, extract the encoder and decoder state dictionaries, and save them to an HDF5 file.

## Summarization

### Requirements

- Python 3.x
- PyTorch
- Transformers library

### Summarization Steps

1. Install the required libraries:

    ```
    pip install torch transformers
    ```

2. Run the summarization script:

    ```
    python summarization.py
    ```

3. The script will load the encoder and decoder state dictionaries, tokenize the input text, generate a summary, and print the cleaned and formatted summary.

## Example Usage

```python
# Example usage of the Summarizer class
summarizer = Summarizer()
input_text = "Your input text goes here..."
generated_summary = summarizer.generate_summary(input_text)
print("Generated Summary: \n", generated_summary)
