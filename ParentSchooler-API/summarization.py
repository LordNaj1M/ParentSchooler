import pandas as pd
import torch
import transformers
from transformers import BertTokenizer, EncoderDecoderModel, EncoderDecoderConfig

# Model initialization and tokenizer
print(torch.__version__) # Torch = 2.0.1
print(transformers.__version__) # Transformers = 4.30.2
model_path = "encoder_decoder_model.h5"
checkpoint = torch.load(model_path)
config = EncoderDecoderConfig.from_pretrained('./fine_tuned_model')
model = EncoderDecoderModel(config=config)
model.encoder.load_state_dict(checkpoint['encoder_state_dict'])
model.decoder.load_state_dict(checkpoint['decoder_state_dict'])
tokenizer = BertTokenizer.from_pretrained("cahya/bert2gpt-indonesian-summarization")

def get_articles_by_keyword(data, user_keyword):
    return data[data['keywords_score_dictionary'].str.contains(user_keyword)]

def get_summary(user_keyword):
    journal_data = "journal_data.csv"
    data = pd.read_csv(journal_data)

    selected_articles = get_articles_by_keyword(data, user_keyword)

    if not selected_articles.empty:
        selected_index = selected_articles['keywords_score_dictionary'].apply(lambda x: eval(x).get(user_keyword, -1)).idxmax()
        selected_article = selected_articles.loc[selected_index]

        selected_abstract = selected_article['abstract']
        selected_citation = selected_article['citation']

        # Tokenization dan Summarization
        input_ids = tokenizer.encode(selected_abstract, return_tensors="pt", max_length=512, truncation=True)
        summary_ids = model.generate(input_ids, max_length=150, min_length=30, num_beams=5, length_penalty=2.0, early_stopping=True)
        summary_text = tokenizer.decode(summary_ids[0], skip_special_tokens=True)

        return {
            "summary": summary_text,
            "citation": selected_citation,
        }
    else:
        return "Error: Keyword not found in journal_data."

print(get_summary("keluarga"))
