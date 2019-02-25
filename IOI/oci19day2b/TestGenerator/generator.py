from random import randint

class Config:
  def __init__(self, N, Q, M):
    self.N = N
    self.Q = Q
    self.M = M

def main():
  # config = Config(10, 10, 10)
  # config = Config(10**5, 10**5, 10**6)
  config = Config(10**4, 10**4, 10**6)
  
  out = open('%d.in' % config.N, 'w+')

  out.write('%d\n' % config.N)
  for i in range(config.N-1):
    out.write('%d ' % randint(1, config.M))
  out.write('%d\n' % randint(1, config.M))

  out.write('%d\n' % config.Q)
  for i in range(config.Q):
    length = randint(1, config.N)
    start = randint(1, config.N - length + 1)
    out.write('{start} {end}\n'.format(start=start, end=start + length - 1))
  out.close()

if __name__ == '__main__':
  main()
