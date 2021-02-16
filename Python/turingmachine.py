#Purpose: To create a turing machine that takes user input
#Author: Sean Poston
#Class: CS345 HW3
#Date: 9/26/2020 8:00PM (original code for Discussion 5)
#      10/4/2020 10:40AM (updated for HW3)

###---IMPORTS---###
from time import sleep
              
###---CLASS TAPE---###
class Machine:
    #Constructor
    def __init__(self, tuples, state = 'A', tape = ' ', maxRuns = 10):
        self.state = state
        self.tape = tape
        self.tuples = tuples
        self.maxRuns = maxRuns
        self.position = 0
        self.currentTuple = 0
        
    #Move Position Right by incrementing array position 1
    def moveRight(self):
        print(f'\n"{self.state}" Move Right:')
        
        self.position += 1
        self.showTape()
    
    #Move Position Left by decrementing array position 1
    def moveLeft(self):
        print(f'\n"{self.state}" Move Left:')
        self.position -= 1
        self.showTape()
        
    #Replace current position's value with x
    def rewrite(self, x):
        print(f'\n"{self.state}" Write:')
        
        s = list(self.tape)
        s[self.position] = x
        self.tape = s
        self.showTape()
    
    #Print tape with current position pointer
    def showTape(self):
        for x in self.tape:
            print(x, end = " ")
        print()
        for x in range(self.position + 1):
            if x != self.position:
                print("  ", end = "")
            else:
                print("^")
        sleep(1)
        
    #Run the machine with given arguments
    def run(self):
        while self.maxRuns > 0:
            #Current tuple, analogous to current State, tuple 1
            currentList = list(self.tuples[self.currentTuple])
            
            read = currentList[1]
            write = currentList[2]
            move = currentList[3]
            nextState = currentList[4]    

            #Read and write, tuple 2 and 3
            if len(self.tape) > 0:
                if self.tape[self.position] == read or read == ' ':
                    self.rewrite(write)
            else:
                if read == ' ':
                    self.rewrite(write)
                    
            #Move, tuple 4
            if move == 'L':
                self.moveLeft()
            elif move == 'R':
                self.moveRight()
                
            #Next State, tuple 5
            for i in range(len(self.tuples)):
                if nextState is list(self.tuples[i])[0]:
                    #only sets next state if it will run again
                    #this is to preserve the current state to print as final state
                    if self.maxRuns > 1:
                        self.state = nextState
                        self.currentTuple = i
                    break
                #if it's the last item in the list of tuples and it hasn't found
                #the next state yet, it will exit with error message
                elif i + 1 is len(self.tuples) and nextState is not list(self.tuples[i])[0]:
                    print('HALT - STATE NOT FOUND')
                    print(f'FINAL STATE: {self.state}')
                    exit(1)
            
            self.maxRuns -= 1
        print('HALT - MACHINE FINISHED')
        print(f'FINAL STATE: {self.state}')

###---CLASS MAIN---###
def main():
    tuples = []
    tupleInput = ''
    while (tupleInput != '.'):
        tupleInput = input('Enter tuple or "." to complete input: ')
        if (tupleInput == '.'):
            break
        tuples.append(tupleInput)
    
    initTape = input('Enter the initial tape: ')
    
    maxRuns = int(input('Enter the max amount of runs: '))
    
    #Make sure initTape isn't blank
    #Modify initTape to simulate infinite tape
    #This will only work for single steps as is
    #edit: upon further thought, this should work for all machines
    #however, it would be better to increase the string as needed in the function
    #this would simulate unbounded.
    if len(initTape) == 0:
        for i in range(maxRuns):
            initTape += ' '
    
    #Get starting state
    startingState = list(tuples[0])[0]
    
    machine = Machine(tuples, startingState, initTape, maxRuns)
    print('Starting Tape:')
    machine.showTape()
    print()
    
    machine.run();
main()