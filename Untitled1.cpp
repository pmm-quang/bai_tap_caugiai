#include<iostream>
#include<set>
#include<vector>
#include<algorithm>

using namespace std;

int main(){
	int nA, nB;
	set<int>arrA;
	set<int>arrB;
	
	cin>>nA;
	
	for(int i = 0; i < nA; i++){
		int tmp;
		cin>>tmp;
		arrA.insert(tmp);
	}
	
	cin>>nB;
	
	for(int i = 0; i < nB; i++){
		int tmp;
		cin>>tmp;
		arrB.insert(tmp);
	}
	
	vector<int>arrAB;
	
	set_intersection(arrA.begin(), arrA.end(), arrB.begin(), arrB.end(), back_inserter(arrAB));
	for(int i = 0; i< arrAB.size(); i++){
		cout<<arrAB[i]<<' ';
	}
	
	return 0;
}
