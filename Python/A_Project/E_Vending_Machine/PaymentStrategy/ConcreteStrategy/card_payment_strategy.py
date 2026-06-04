from datetime import date

from PaymentStrategy.payment_strategy import PaymentStrategy


class CardPaymentStrategy(PaymentStrategy):
    def __init__(self, card_number: str, expiry_date: str):
        sanitized = card_number.replace(" ", "") if card_number else ""
        if not sanitized.isdigit() or not (13 <= len(sanitized) <= 19):
            raise ValueError("Invalid card number")

        month, year = self._parse_expiry_date(expiry_date)
        if self._is_expired(month, year):
            raise ValueError("Card has expired")

        self.card_number = sanitized
        self.expiry_month = month
        self.expiry_year = year

    def process_payment(self, amount: int) -> bool:
        if amount <= 0:
            return False
        if self._is_expired(self.expiry_month, self.expiry_year):
            return False
        last4 = self.card_number[-4:]
        print(
            f"Processing card payment of amount: {amount} with card ending in {last4}"
        )
        return True

    def _parse_expiry_date(self, expiry_date: str):
        if not expiry_date or "/" not in expiry_date:
            raise ValueError("Invalid expiry date")
        parts = expiry_date.split("/")
        if len(parts) != 2:
            raise ValueError("Invalid expiry date")
        try:
            month = int(parts[0])
            year = int(parts[1])
        except ValueError as exc:
            raise ValueError("Invalid expiry date") from exc
        if month < 1 or month > 12:
            raise ValueError("Invalid expiry date")
        if year < 100:
            year += 2000
        return month, year

    def _is_expired(self, month: int, year: int) -> bool:
        today = date.today()
        return (year, month) < (today.year, today.month)
