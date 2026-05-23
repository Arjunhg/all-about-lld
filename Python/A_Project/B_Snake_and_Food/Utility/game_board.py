class GameBoard:
    _instance = None

    def __init__(self, width: int, height: int):
        if width <= 0 or height <= 0:
            raise ValueError("width and height must be positive")
        self.width = width
        self.height = height

    @classmethod
    def get_instance(cls, width: int, height: int):
        if cls._instance is None:
            cls._instance = cls(width, height)
        elif cls._instance.width != width or cls._instance.height != height:
            raise ValueError(
                f"GameBoard already initialized as "
                f"{cls._instance.width}x{cls._instance.height}, got {width}x{height}"
            )
        return cls._instance

    def get_width(self) -> int:
        return self.width

    def get_height(self) -> int:
        return self.height
