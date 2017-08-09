#include <thread>

extern "C" {
  #include "nimcache/lib.h"
}

void flatExample() {
  flat((char*) "flatExample");
}

void nested() {
  int x = 0;
  noTeardown((char*) "nested");
}

void noTeardownExample() {
  nested();
  noTeardown((char*) "noTeardownExample");
}

int main(int argc, char** argv) {
  NimMain();

  std::thread t1(flatExample);
  t1.join();

  std::thread t2(noTeardownExample);
  t2.join();
}
