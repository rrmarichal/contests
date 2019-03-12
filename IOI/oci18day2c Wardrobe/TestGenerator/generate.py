import sys
from random import randint

class Config:
  def __init__(self, M, D, rng):
    self.M = M
    self.D = D
    self.rng = rng

def generate(M, D, rng):
    config = Config(M, D, rng)
    sys.stdout.write('1 {M} {D}\n'.format(M = config.M, D = config.D))
    sys.stdout.write('{K} '.format(K = config.M))
    for i in range(config.M - 1):
        sys.stdout.write('{x} '.format(x = randint(config.rng[0], config.rng[1])))
    sys.stdout.write('{x}\n'.format(x = randint(config.rng[0], config.rng[1])))
    sys.stdout.close()

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print('Usage: generate {M} {D} {range}?')
    else:
        if (len(sys.argv) == 5):
            rng = (int(sys.argv[3]), int(sys.argv[4]))
        else:
            rng = (1, 1000)
        generate(int(sys.argv[1]), int(sys.argv[2]), rng)
