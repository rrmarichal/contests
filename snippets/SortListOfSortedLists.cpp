#include <set>
#include <vector>
#include <string>
#include <iostream>

using namespace std;

template <typename T>
class list_item {
	public:
		list_item(T item, int index, int position) : item { item }, index { index }, position { position } {}

		T getItem() const {
			return item;
		}

		int getIndex() const {
			return index;
		}

		int getPosition() const {
			return position;
		}

		bool operator< (list_item<T> other) const {
			if (getItem() != other.getItem()) {
				return getItem() < other.getItem();
			}
			return getIndex() < other.getIndex();
		}

	private:
		T item;
		int index, position;
};

template <typename T>
vector<T>* sort(vector<vector<T>>& lists) {
	set<list_item<T>> mins;
	for (unsigned int j = 0; j < lists.size(); j++) {
		if (lists[j].size() > 0) {
			mins.emplace(list_item<T>(lists[j][0], j, 0));
		}
	}
	
	vector<T>* result = new vector<T>();

	while (mins.size() > 0) {
		auto min = mins.begin();
		result->push_back(min->getItem());
		if (lists[min->getIndex()].size() > min->getPosition() + 1) {
			mins.emplace(list_item<T>(lists[min->getIndex()][min->getPosition() + 1], min->getIndex(), min->getPosition() + 1));
		}
		mins.erase(min);
	}

	return result;
}

int main() {
	vector<vector<string>> lists { { "1", "2", "3", "7" }, { "1", "5", "6" } };
	vector<string>* sorted = sort(lists);
	for (auto& item : *sorted) {
		cout << item.c_str() << endl;
	}
	delete sorted;
	return 0;
}
