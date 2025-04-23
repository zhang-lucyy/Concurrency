#include "SimpleThread.h"
#include <thread>

/**
 * Runs two instances of SimpleThread
 */
int main() {
    SimpleThread thread1("Hi");
    SimpleThread thread2("Ho");

    std::thread hi(&SimpleThread::run, &thread1);
    std::thread ho(&SimpleThread::run, &thread2);

    hi.join();
    ho.join();

    return 0;
}
