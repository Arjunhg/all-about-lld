from MovementStrategyModule.movement_strategy import MovementStrategy


class AIMovementStrategy(MovementStrategy):
    def get_next_position(self, current_head, direction: str):
        return current_head
