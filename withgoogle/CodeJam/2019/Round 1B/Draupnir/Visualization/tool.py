def main():
    for i in range(1, 501):
        print('{}: '.format(i), end='')
        for k in range(1, 7):
            print('{} '.format(i//k), end='')
        print()

if __name__ == "__main__":
    main()
