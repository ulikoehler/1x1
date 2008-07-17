///If we can use libcrypto use this one
#include <MersenneTwister.h>
using namespace boost;
MTRand rand;

int pw_random_number(int max_num)
{
    return rand.randInt() % max_num;
}

