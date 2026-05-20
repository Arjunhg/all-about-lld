class Player:
    def __init__(self, symbol, player_strategy):
        self._symbol = symbol
        self._player_strategy = player_strategy

    def get_symbol(self):
        return self._symbol

    def get_player_strategy(self):
        return self._player_strategy
