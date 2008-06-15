#include <openssl/rsa.h>
#include <openssl/rand.h>

void seedRand(unsigned int bytes)
{
    #ifdef linux /// /dev/random is available
        #ifdef GOODRANDOM //If defined, seed from /dev/random instead of /dev/urandom
            //RAND_load_file("/dev/random", bytes);
        #else
            //RAND_load_file("/dev/urandom", bytes);
        #endif
    #else ///Other OSs Automatically seeded; TODO: Maybe check if /dev/urandom is available

    #endif
}

void generateRsaKey()
{

}
