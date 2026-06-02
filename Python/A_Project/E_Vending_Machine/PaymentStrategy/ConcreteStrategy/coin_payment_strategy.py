from PaymentStrategy.payment_strategy import PaymentStrategy


class CoinPaymentStrategy(PaymentStrategy):
    def __init__(self, inserted_coins):
        self.inserted_coins = inserted_coins

    def process_payment(self, amount: int) -> bool:
        total = sum(coin.get_value() for coin in self.inserted_coins)
        return total >= amount
