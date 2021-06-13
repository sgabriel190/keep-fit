from flask import Flask
from werkzeug.exceptions import HTTPException

from http_helper.Response import Response

app = Flask(__name__)

api_route = "/api/notification"


@app.route(f'{api_route}/ping', methods=['GET'])
def ping():
    return Response(code=200, message="", data=None).to_dict(), 200


@app.errorhandler(Exception)
def handle_exception(err):
    if isinstance(err, HTTPException):
        return Response(400, err.description, err.__dict__).to_dict(), 400
    return Response(code=400, message=str(err), data=None).to_dict(), 400


if __name__ == '__main__':
    app.run()
