import re
import matplotlib.pylab as plt
import matplotlib.lines as mlines

def generator(fileName):
    for word in fileName:
        yield word.replace('\n', '')

def main():
    fileName = open('input.txt', 'r')
    output = open('nr.txt', 'w')
    gen = generator(fileName)

    for word in gen:
        if word[0].lower() == 'n' and word[-1].lower() == 'r':
            output.write(f'{word}\n')

main()