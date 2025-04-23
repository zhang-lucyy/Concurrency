#ifndef SIMPLETHREAD_H
#define SIMPLETHREAD_H

#include <iostream>
#include <string>

class SimpleThread {
    private:
        std::string name;
    public:
        SimpleThread(std::string threadName);
        void run();
};

#endif
