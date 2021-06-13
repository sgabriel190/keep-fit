import os

from flask import Flask, request, jsonify
from werkzeug.exceptions import HTTPException

from SMTPServer import SMTPServer
from http_helper.Response import Response

app = Flask(__name__)

api_route = "/api/notification"
smtp_connection = SMTPServer(os.getenv("EMAIL_NAME"), os.getenv("EMAIL_PASSWORD"))


@app.route(f'{api_route}/ping', methods=['GET'])
def ping():
    return Response(code=200, message="", data=None).to_dict(), 200


@app.route(f'{api_route}/send/mail', methods=['POST'])
def send_email():
    data: dict = request.json
    if data is None:
        raise Exception("No data sent to the operation.")
    if 'message' not in data or 'to' not in data:
        raise Exception("The data is not formatted correctly.")
    smtp_connection.send_email(data["to"], data["message"])
    return jsonify(None), 204


@app.errorhandler(Exception)
def handle_exception(err):
    if isinstance(err, HTTPException):
        return Response(400, err.description, err.__dict__).to_dict(), 400
    return Response(code=400, message=str(err), data=None).to_dict(), 400


if __name__ == '__main__':
    try:
        app.run()
    except Exception as exc:
        smtp_connection.close_connection()
