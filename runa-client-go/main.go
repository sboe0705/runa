package main

import (
	"bufio"
	"fmt"
	"os"
	"os/exec"
	"runtime"
)

var reader = bufio.NewReader(os.Stdin)
var name string

func main() {
	clearConsole()

	login()

	for {
		fmt.Println("\nOptions:")
		fmt.Println("1. Login (with different name)")
		fmt.Println("2. Send message")
		// fmt.Println("3. Nachrichten abrufen")
		fmt.Println("4. Exit")
		fmt.Print("Select an option: ")

		option := readString()
		clearConsole()

		switch option {
		case "1":
			login()
		case "2":
			fmt.Print("Receiver: ")
			receiver := readString()
			fmt.Println("Message:")
			message := readString()
			fmt.Printf("Sending message '%s' to %s.\n", message, receiver)
			// case "3":
			// 	fmt.Println("\nGespeicherte Nachrichten:")
			// 	if len(messages) == 0 {
			// 		fmt.Println("Keine Nachrichten vorhanden.")
			// 	} else {
			// 		for _, msg := range messages {
			// 			fmt.Println(msg)
			// 		}
			// 	}
		case "4":
			os.Exit(0)
		default:
			fmt.Println("Invalid input! Please try again.")
		}

	}

}

func login() {
	fmt.Print("Login name: ")
	name = readString()
}

func readString() string {
	value, _ := reader.ReadString('\n')
	value = value[:len(value)-1] // remove new-line
	return value
}

func clearConsole() {
	switch runtime.GOOS {
	case "windows":
		cmd := exec.Command("cmd", "/c", "cls")
		cmd.Stdout = os.Stdout
		cmd.Run()
	default:
		fmt.Print("\033[H\033[2J")
	}
}
