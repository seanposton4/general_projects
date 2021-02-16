#include <stdio.h>
#include <ctype.h>
#include <string.h>

int main(void){
	char notes[12][2] = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", 
		"A", "A#", "B"};
	char key[2];
	int startingPosition;
	printf("Enter a key: ");
	scanf("%2s", key);
	key[0] = toupper(key[0]);
	
	printf("Key is: %s\n\n", key);
	
	for (int i = 0; i < 12; i++) {
		if (strcmp(key, notes[i]) == 0){
			startingPosition = i;
		}
	}
	
	int x = startingPosition;
	char keyList[7][2] = {*notes[x], *notes[(x + 2) % 11], *notes[(x + 4) % 11],
		*notes[(x + 5) % 11], *notes[(x + 7) % 11], *notes[(x + 9) % 11],
		*notes[(x + 11) % 11]};
	
	for (int i = 0; i < 7; i++) {
		printf("keyList [%d]: %s", i, keyList[i]);
	}
	printf("\n\n");
	int chord;
	printf("Enter a chord position: ");
	scanf("%d", &chord);
	
	printf("Chord Notes: %s, %s, %s", keyList[chord], keyList[chord + 1],
		keyList[chord + 2]);
	return 0;
}