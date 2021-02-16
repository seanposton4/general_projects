#include <stdio.h>

int main(){
    int x = 15;
    printf("%d", x);

    int list [] = {0, 1, 2};
    for (int i = 0; i < 3; i ++) {
        printf("\nList %d: %d\n", i, list[i]);
    }

    int y;
    printf("Enter a thing: ");
    scanf("%d", &y);

    printf("y: %d", y);
    return 0;
}