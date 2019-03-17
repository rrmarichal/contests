. Divide K in chunks of 2^8 bits (k is at most 10^18, according to the task specification).
. The main idea is behind the following observation:
	+ Lets call P = 'FESTIVAL'.
	+ Let S be some string, and let C be the number of occurrences of P as a subsequence of S.
	+ Let F = {s_f1, s_f2, ..., s_f8} be the first occurrence of P in S.
	=> Inserting K characters 'L' after right after position f8 increases the number of occurrences of P in S by K.
. Generating the output string for given K goes as follows:
	+ Let q = K / 2^8
	+ Let r = K % 2^8
	+ Recursively generate a string S' with q occurrences of P using one less character.
	+ Append 2^8 characters to the end of S'
	+ At this point we have S' with count q*2^8
	+ Adding r occurrences follows from the observation above inserting r characters at the first
		occurrence of the patternw