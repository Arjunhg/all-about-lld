from PaymentStrategy.payment_strategy import PaymentStrategy


class CardPaymentStrategy(PaymentStrategy):
    def __init__(self, card_number: str, expiry_date: str, cvv: str):
        self.card_number = card_number
        self.expiry_date = expiry_date
        self.cvv = cvv

    def process_payment(self, amount: int) -> bool:
        print(f"Processing card payment of amount: {amount}")
        return True
