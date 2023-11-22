import os

try:
    import inquirer 
except ModuleNotFoundError:
    os.system("pip3 install inquirer")


# Compile MovieSearch.
# os.system("javac -d bin -cp bin src/MovieSearch.java")

def choice_prompt():
    test_file1 = "movies.txt"
    test_file2 = "shortmovielist.txt"

    cyan_choice = f'\x1b[36m{test_file1}\x1b[0m'
    blue_choice = f'\x1b[34m{test_file2}\x1b[0m'

    print()
    questions = [
        inquirer.List('choice',
                      message="Choose a test file",
                      choices=[cyan_choice, blue_choice]
                      ),
    ]

    answers = inquirer.prompt(questions)
    return answers


choice = choice_prompt()

print(f"You chose: {choice['choice']}")
print("Opening application...\n")

if choice['choice'][5:-4] == "shortmovielist.txt":
    os.system("java -cp bin MovieSearch test_files/shortmovielist.txt")
else:
    os.system("java -cp bin MovieSearch test_files/movies.txt")