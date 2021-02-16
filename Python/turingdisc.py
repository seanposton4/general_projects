#Purpose: Check if binary number is a palindrome
#Author: Sean Poston
#Class: CS345 Discussion 5
#Date: 9/26/2020 8:00PM

###---IMPORTS---###
from time import sleep
              
###---CLASS TAPE---###
class Machine:
    #Constructor
    def __init__(self, state = 'A', tape = ''):
        self.state = state
        self.tape = tape
        self.position = 0
        
    #Move Position Right by incrementing array position 1
    def moveRight(self):
        print(f'\n"{self.state}" Move Right:')
        self.position += 1
        self.showTape()
        
    #Replace current position's value with x
    def rewrite(self, x):
        print(f'\n"{self.state}" Rewrite:')
        
        s = list(self.tape)
        s[self.position] = x
        self.tape = s
        self.showTape()
        
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
        
    def run(self):
        while self.position < len(self.tape) - 1:
            if self.state == 'A':
                if self.tape[self.position] == '0':
                    self.rewrite('x')
                    self.state = 'A'
                else:
                    self.moveRight()
                    self.state = 'B'
            if self.state =='B':
                if self.tape[self.position] == '1':
                    self.rewrite('0')
                    self.state = 'B'
                else:
                    self.moveRight()
                    self.state = 'A'
        print('HALT')

###---CLASS MAIN---###
def main():
    machine = Machine('A', '01000111')
    print('Starting Tape:')
    machine.showTape()
    print()
    
    machine.run();
main()