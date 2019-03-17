const
	MAXL=2000;
	MAXC=200;

var
	alpha, s1, s2 : string;
	alphal, s1l, s2l : integer;
	P : array[#33..#255] of integer;
	D : array[1..MAXC, 1..MAXC] of integer;
	minr, minc, minrpoz, mincpoz : array[1..MAXL] of integer;
	T, W : array[0..MAXL, 0..MAXL] of integer;

function solve : integer;
var
	j, k : integer;
begin
	for j:= 1 to alphal do begin
		minr[j]:= maxint;
		for k:= 1 to alphal do
			if (D[j][k]<minr[j])
				then begin
					minr[j]:= D[j][k];
					minrpoz[j]:= k;
				end;
	end;
	for k:= 1 to alphal do begin
		minc[k]:= maxint;
		for j:= 1 to alphal do
			if (D[j][k]<minc[k])
				then begin
					minc[k]:= D[j][k];
					mincpoz[k]:= j;
				end;
	end;
	T[0][0]:= 0;
	for j:= 1 to s2l do begin
		T[0][j]:= T[0][j-1]+minc[P[s2[j]]];
		W[0][j]:= 3;
	end;
	for j:= 1 to s1l do begin
		T[j][0]:= T[j-1][0]+minr[P[s1[j]]];
		W[j][0]:= 2;
	end;
	for j:= 1 to s1l do
		for k:= 1 to s2l do begin
			T[j][k]:= T[j-1][k-1]+D[P[s1[j]]][P[s2[k]]];
			W[j][k]:= 1;
			if (T[j][k]>T[j][k-1]+minc[P[s2[k]]])
				then begin
					T[j][k]:= T[j][k-1]+minc[P[s2[k]]];
					W[j][k]:= 3;
				end;
			if (T[j][k]>T[j-1][k]+minr[P[s1[j]]])
				then begin
					T[j][k]:= T[j-1][k]+minr[P[s1[j]]];
					W[j][k]:= 2;
				end;
		end;
	solve:= T[s1l][s2l];
end;

procedure pout;
var
	j, k : integer;
	os1, os2 : string;
begin
	j:= s1l; k:= s2l;
	os1:= ''; os2:= '';
	while ((j>0) or (k>0)) do begin
		case (W[j][k]) of
			1: begin
				os1:= s1[j]+os1;
				os2:= s2[k]+os2;
				dec(j); dec(k);
			end;
			2: begin
 				os1:= s1[j]+os1;
				os2:= alpha[minrpoz[P[s1[j]]]]+os2;
				dec(j);
			end;
			3: begin
				os1:= alpha[mincpoz[P[s2[k]]]]+os1;
				os2:= s2[k]+os2;
				dec(k);
			end;
		end;
	end;
	writeln(os1);
	writeln(os2);
end;

var
	j, k : integer;
begin
	readln(alpha);
	alphal:= length(alpha);
	fillchar(P, sizeof(P), 255);
	for j:= 1 to alphal do
		P[alpha[j]]:=j;
	readln(s1);
	readln(s2);
	s1l:= length(s1);
	s2l:= length(s2);
	for j:= 1 to alphal do
		for k:= 1 to alphal do
			read(D[j][k]);
	writeln(solve);
	pout;
end.
