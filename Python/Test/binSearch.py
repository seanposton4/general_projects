# def binarySearch(arr, key, low, high):
#     #low, high will be first, last of array
#     #key is value to be found
#     if low > high:
#         return -1
#     else:
#         mid = (high + low) // 2
#         if arr[mid] == key:
#             return mid
#         elif arr[mid] > key:
#             return binarySearch(arr, key, low, mid - 1)
#         else:
#             return binarySearch(arr, key, mid + 1, high)

def binarySearch(list, key):
    list.sort()
    return search(list, key, 0, len(list))

def search(list, key, low, high):
    if low > high:
        return -1
    else:
        mid = (high + low) // 2
        if list[mid] > key:
            return search(list, key, low, mid - 1)
        elif list[mid] < key:
            return search(list, key, mid + 1, high)
        else:
            return mid #return index of key

if __name__ == '__main__':
    list = []
    for i in range(20):
        list.append(i)

    print(f'List: {list}')
    for i in list:
        print(f'{i} ', end = '')
    print()
    key = binarySearch(list, 8)
    print(f'Key: {key}')
    print(f'Sorted List: {list}')