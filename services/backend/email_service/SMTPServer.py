import smtplib
import ssl


class SMTPServer:

    def __init__(self, username: str, password: str):
        self.__context = ssl.create_default_context()
        self.__port: int = 465
        self.__smtp_server: str = "smtp.gmail.com"
        self.__sender_email: str = username
        self.__connection = smtplib.SMTP_SSL(
            self.__smtp_server,
            self.__port,
            context=self.__context
        )
        self.__connection.login(self.__sender_email, password)

    def send_email(self, receiver_email, message) -> None:
        self.__connection.sendmail(self.__sender_email, receiver_email, message)

    def close_connection(self) -> None:
        self.__connection.close()
