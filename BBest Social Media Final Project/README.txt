(going downwards in the package structure)

My Account class uses two linked lists, posts and followed accounts, I use this for posts because any time
I need to view a user's posts in the code, I will need all of them so the big O is n, along with this
if I had used a tree implementation, the big O to retrieve all of them would be nLogn, another reason this
is good is because of the space used, as a linked list always uses exactly as much space as is needed to 
store the information and is scalable to large amounts with little detriment.

My Account class uses a linked list for followed accounts for the same reason as above, always need all of
them, big o better than trees, better space complexity.

My AccountTree class implements a tree map because I will always need to search for accounts and never need
to retrieve all of them, this results in a big o of logn and the space complexity is better than a normal 
array because again, it only uses exactly as much space as it needs and is always sorted which is perfect for
my needs in this program.

My Post class has a linked list of comments for the same reason as the account has a linked list of posts
I always need all of them, big o better than trees, better space complexity

In my utilities I used a hashset and linked list
I used a hashset for the dictionary as it ensured a big o of 1 for all of my uses, I used a linked list for
the returned list of words that were misspelled because I need all of them and i don't know the length it 
would need to this is best in terms of big o, and space

Every case in my views where I used a linked list is because of the above reasons, need every value (sometimes
in sequential order and linked list is automatically sorted by order so that makes it even better), has better
big o and space complexity than trees or arrays