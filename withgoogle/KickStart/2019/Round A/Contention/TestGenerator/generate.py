import sys
from random import randint

class Config:
  def __init__(self, N, Q):
    self.N = N
    self.Q = Q

def generate(N, Q):
    config = Config(N, Q)
    sys.stdout.write('{N} {Q}\n'.format(N = config.N, Q = config.Q))
    for i in range(config.Q):
        L = randint(1, config.N)
        l = min(config.N - L, config.N // 5)
        sys.stdout.write('{L} {R}\n'.format(L = L, R = L + l))

if __name__ == "__main__":
    if len(sys.argv) < 4:
        print('Usage: generate {T} {N} {Q}?')
    else:
        sys.stdout.write('{T}\n'.format(T = sys.argv[1]))
        for _ in range(int(sys.argv[1])):
            generate(int(sys.argv[2]), int(sys.argv[3]))
        sys.stdout.close()
