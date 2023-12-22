from flask import Flask, request, jsonify
from summarization import get_summary

# Initialize Flask
app = Flask(__name__)

# Initialize Flask server (file summarization.py)
@app.route("/", methods=["GET"])
def welcome():
    return "Welcome to our API"

@app.route("/summary", methods=["POST"])
def summarize():
    input = request.json.get('input')
    if not input:
        return jsonify(error="Input is missing"), 400

    return jsonify(output = get_summary(input))

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080, debug=True)
