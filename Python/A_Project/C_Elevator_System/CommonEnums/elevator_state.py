from enum import Enum


class ElevatorState(Enum):
    IDLE = "IDLE"
    MOVING = "MOVING"
    STOPPED = "STOPPED"
    MAINTENANCE = "MAINTENANCE"
