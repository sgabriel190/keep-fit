import smtplib
import ssl
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText


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

    def send_email(self, receiver_email: str, message: str, subject: str) -> None:
        tmp = MIMEMultipart("alternative")
        tmp["Subject"] = subject
        tmp["From"] = self.__sender_email
        tmp["To"] = receiver_email
        msg_part_html = MIMEText(message, "html")
        msg_part_plain = MIMEText(message, "plain")
        tmp.attach(msg_part_plain)
        tmp.attach(msg_part_html)
        self.__connection.sendmail(self.__sender_email, receiver_email, tmp.as_string())

    def close_connection(self) -> None:
        self.__connection.close()
