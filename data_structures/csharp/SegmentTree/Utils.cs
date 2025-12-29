namespace SegmentTree {

	public static class Utils {

		public static int NextPowerOfTwo(int value) {
			if (value == 0) {
				return 1;
			}
			// Check value is a power of two.
			if ((value & (value - 1)) == 0) {
				return value;
			}
			int trail = 0;
			while (value > 0) {
				trail++;
				value >>= 1;
			}
			return 1 << trail;
		}

	}

}
