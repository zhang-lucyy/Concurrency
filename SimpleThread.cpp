#include "SimpleThread.h"
#include <cstdlib>
#include <ctime>
#include <thread>
#include <syncstream>
#include <random>

/**
 * Note: Make sure to compile using c++ version 20+ because you can only run osyncstream with
 * versions 20+
 */
SimpleThread::SimpleThread(std::string threadName) : name(threadName) {}

void SimpleThread::run() {
    // random number generator
    thread_local std::random_device random;
    thread_local std::mt19937 gen(random());
    std::uniform_real_distribution<double> dist(0.0, 1.0);

    for (int i = 1; i <= 10; i++) {

        // generate a time
        double time = dist(gen);
        std::this_thread::sleep_for(std::chrono::milliseconds(static_cast<int>(time*1000)));
        
        if (i < 10) {
            std::osyncstream(std::cout) << i << " " << name << std::endl;
        } else {
            std::osyncstream(std::cout) << "DONE! " << name << std::endl;
        }
    }
}