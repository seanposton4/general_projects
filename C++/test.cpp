#include <iostream>
#include <chrono>
using namespace std;
using namespace std::chrono;

void printFunc (int n) {
    auto start = high_resolution_clock::now();
    int x = 0;
    while (x <= n) {
        cout << x << endl;
        x++;
    }
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);
    cout << "Print Func Runtime: " << duration.count() << endl;
}

void calcFunc (int n) {
    auto start = high_resolution_clock::now();
    int x = 0;
    while (x <= n) {
        x++;
    }
    cout << x << endl;
    auto stop = high_resolution_clock::now();
    auto duration = duration_cast<milliseconds>(stop - start);
    cout << "Calc Func Runtime: " << duration.count() << endl;
}

int main() {
    int x;
    cout << "Enter x: ";
    cin >> x;
    cout << "Print Function: " << endl;
    printFunc(x);
    cout << endl << "Calc Function: " << endl;
    calcFunc(x);

    return 0;
}