class GameBoard:
    _instance = None

    def __init__(self, width: int, height: int):
        self.width = width
        self.height = height

    @classmethod
    def get_instance(cls, width: int, height: int):
        if cls._instance is None:
            cls._instance = cls(width, height)
        return cls._instance

    def get_width(self) -> int:
        return self.width

    def get_height(self) -> int:
        return self.height
