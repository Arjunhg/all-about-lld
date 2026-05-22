from Controller.snake_game import SnakeGame


def convert_input(user_input: str) -> str:
	if user_input == "W":
		return "U"
	if user_input == "S":
		return "D"
	if user_input == "A":
		return "L"
	if user_input == "D":
		return "R"
	return ""


def main():
	width = 10
	height = 10

	food_positions = [
		[5, 5],
		[9, 8],
		[3, 7],
		[8, 1],
		[2, 3],
	]

	game = SnakeGame(width, height, food_positions)

	print("===== SNAKE GAME =====")
	print("Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)")
	print("Eat food to grow your snake and increase your score.")
	print("Don't hit the walls or bite yourself!")
	print("=======================")

	game_running = True
	while game_running:
		user_input = input("Enter move (W/A/S/D) or Q to quit: ").strip().upper()

		if user_input == "Q":
			print(f"Thanks for playing! Final Score: {game.get_score()}")
			game_running = False
			continue

		direction = convert_input(user_input)
		if direction == "":
			print("Invalid input! Please use W/A/S/D to move or Q to quit.")
			continue

		score = game.move(direction)
		if score == -1:
			print(f"Game Over! Final Score: {game.get_score()}")
			game_running = False
		else:
			print(f"Current Score: {game.get_score()}")

	print("Thanks for playing!")


if __name__ == "__main__":
	main()
