public class SplayTree<Key extends Comparable<Key>> 
{
	/*
		FASTAYR�ING GAGNA:
		Node er hlekkur � Splay tr�
		Node inniheldur heilt�lu gildi
		Node inniheldur tilv�sun � vinstra og h�gra barn sitt (m� vera null) og foreldri sitt(m� vera null)
		B�rn og foreldrar eru a�rir hlekkir (Nodes), ef �au eru til.
		Splay tr� hlutur inniheldur hlekki af taginu Node. 
		Efsti hlekkurinn (root) � ekkert foreldri.
		Lauf eiga engin b�rn.
		root er hlekkur sem er r�t Splay tr�s (m� vera null).
		
		A� auki uppfyllir tr�� tv�leitartrj�askilyr�i:
			�ll gildi � vinstra undirtr� eru alltaf (fyrir �ll undirtr�)
			< r�targildi og �ll gildi � h�gra undirtr� (fyrir �ll undirtr�)
			eru > r�targildi.
		Tv� (e�a fleiri) eins gildi eru ekki leyf�.
		
	*/
        private class Node 
        {
                private Key key;
                private Node left, right, parent;

                /*
			Notkun: Node node = new Node(key);
			Eftir : node er n�r hlekkur � tr�, sem inniheldur gildi� key
                */
                public Node(Key key) 
                {
                        this.key = key;
                }
                
                /*
			Notkun: int i = node.leafCount();
			Fyrir : Node hlutur er til
			Eftir : i inniheldur fj�lda laufa � node
                */
                public int leafCount()
                {
			int counter = 0;
			if(left != null)
				counter++;
			if(right != null)
				counter++;
			return counter;
                }
        }
        private Node root; 

        /*
		Notkun: st = new SplayTree<Integer>();
		Eftir: st er n�tt t�mt Splay tr�
        */
        public SplayTree()
        {
		root = null;
        }
        

	/*
		Notkun: st.printTree();
		Fyrir : st er Splay tr�
		Eftir : B�i� er a� prenta �t gildin � st
	*/
        public void printTree()
        {
		InOrderTreeWalk(root);
        }
        
        /*
		Notkun: InOrderTreeWalk(n);
		Fyrir: n er hlekkur af tagi Node � Splay tr�
		Eftir: B�i� er a� ganga � gegnum Splay tr� � in-order r�� 
			og prenta �t gildin � Splay tr�nu
        */
        private void InOrderTreeWalk(Node n)
        {
		if(n != null){
			InOrderTreeWalk(n.left);
			System.out.println(n.key);
			InOrderTreeWalk(n.right);
		}
        }
        
        /*
		Notkun: st.put(key);
		Fyrir : st er Splay tr� (m� vera t�mt), key er heiltala
		Eftir : B�i� er a� b�ta gildi key � r�ttan sta� � Splay tr�nu
        */
        public void put(Key key) 
        {
		Node node = new Node(key);
		if(root == null)
			root = node;
                else{
			putRecursive(root,node);
		}
        }

        /*
		Notkun: putRecursive(oldNode,newNode);
		Fyrir : oldNode & newNode eru hlekkir af tagi Node.
			oldNode er hlekkur � Splay tr�
		Eftir: B�i� er a� b�ta newNode vi� Splay tr�� � r�ttum sta� 
			ef newNode er ekki til � Splay tr�nu, newNode er n�ja r�t Splay tr�sins. 
			Ef newNode var til � tr�nu �� er s� hlekkur sem var eins og newNode
			or�in a� r�t tr�sins.
        */
        private void putRecursive(Node oldNode, Node newNode)
        {
		
		switch(oldNode.key.compareTo(newNode.key))
		{
			case 1:
				if(oldNode.left == null){
					oldNode.left = newNode;
					newNode.parent = oldNode;
					Splay(newNode);
					root = newNode;
					return;
				}
				else {
					putRecursive(oldNode.left,newNode);
				}
			case 0: 
				get(newNode.key);
				return;
			case -1:
				if(oldNode.right == null){
					oldNode.right = newNode;
					newNode.parent = oldNode;
					Splay(newNode);
					root = newNode;
					return;
				}
				else {
					putRecursive(oldNode.right,newNode);
				}
		}
        }
        
        /*
		Notkun: s = st.get(key);
		Fyrir: st er Splay tr�, key er heiltala
		Eftir: B�i� er a� s�kja hlekkinn sem inniheldur gildi� key.
			Ef s� hlekkur er til �� er hann or�inn r�t Splay tr�sins 
			og strengnum T er skila�. Annars er strengnum F skila�.
        */
        public String get(Key key) 
        {
		if(root == null)
			return "F";
                else{
			Node nodeToGet = findNode(key,root);
			if(key.equals(nodeToGet.key)){
				Splay(nodeToGet);
				root = nodeToGet;
				return "T";
			}
			else
				Splay(nodeToGet);
				root = nodeToGet;
				return "F";
	        }
	              
        }

        /*
		Notkun: n = findNode(key,node);
		Fyrir: key er heiltala, node er hlekkur � Splay tr�
		Eftir: n er hlekkur � Splay tr� sem inniheldur gildi� key, annars er
			n null ef engin hlekkur � Splay tr� inniheldur gildi� key.
		
        */
	private Node findNode(Key key, Node node)
	{
		if(node != null) {
			switch(node.key.compareTo(key)){
				case 1:
					if(node.left != null){
						return findNode(key, node.left);
					}
					else
						return node;
				case 0:
					return node;
				case -1:
					if(node.right != null){
						return findNode(key, node.right);
					}
					else
						return node;
				default:
					return null;
			}
		}
		else
			return null;
	}
	
	/*
		Notkun: b = st.delete(key);
		Fyrir: key er heiltala
		Eftir: b er b�lkst gildi
			b er True ef t�kst a� ey�a hlekknum sem inniheldur key 
			b er False ef hlekkur er ekki til
	*/
	public boolean delete(Key key) 
        {
		if(root == null)
			return false;
		
    else{
			Node nodeToDelete = findNode(key,root);
			if(nodeToDelete != null && key.equals(nodeToDelete.key)) {
				switch(nodeToDelete.leafCount()) {
					case 0:
						zeroLeafs(nodeToDelete);
						return true;
					case 1:
						oneLeafs(nodeToDelete);
						return true;
					case 2:
						twoLeafs(nodeToDelete);
						return true;
					default:
						return false;
				}
			}
			else 
				return false;
		}
        }
        
        /*
		Notkun: zeroLeafs(node);
		Fyrir: node er hlekkur sem � a� ey�a �r Splay tr�nu
			hlekkurinn node hefur engin lauf
		Eftir: B�i� er a� ey�a hlekknum node �r Splay tr�nu 
        */
	private void zeroLeafs(Node nodeToDelete)
	{
		Node parentOfNodeToDelete = nodeToDelete.parent;

		if(nodeToDelete.parent != null) {
			
			if(nodeToDelete == parentOfNodeToDelete.left){
				parentOfNodeToDelete.left = null;
				//nodeToDelete = null;
			}
			else {
				parentOfNodeToDelete.right = null;
				//nodeToDelete = null;
			}
		}
		else{
			// ey�a r�tinni
			root = null;
			nodeToDelete = null;
		}
	}
	
	/*
		Notkun: oneLeafs(node);
		Fyrir: node er hlekkur sem � a� ey�a �r Splay tr�nu
			hlekkurinn node hefur n�kv�mlega eitt lauf
		Eftir: B�i� er a� ey�a hlekknum node �r Splay tr�nu 
        */
	private void oneLeafs(Node nodeToDelete)
	{
		if(nodeToDelete.parent != null){
			if(nodeToDelete.left != null){
				if(nodeToDelete == nodeToDelete.parent.left){
					nodeToDelete.left.parent = nodeToDelete.parent;
					nodeToDelete.parent.left = nodeToDelete.left;
				}
				else{
					nodeToDelete.left.parent = nodeToDelete.parent;
					nodeToDelete.parent.right = nodeToDelete.left;
				}
			}
			else{
				if(nodeToDelete == nodeToDelete.parent.right){
					nodeToDelete.right.parent = nodeToDelete.parent;
					nodeToDelete.parent.right = nodeToDelete.right;
					
				}
				else{
					nodeToDelete.right.parent = nodeToDelete.parent;
					nodeToDelete.parent.left = nodeToDelete.right;
				}
			}
		}
		else{
			//N��an til a� ey�a er r�tin � tr�nu
			if(nodeToDelete.left != null){
				root = nodeToDelete.left;
				nodeToDelete = null;
			}
			else{
				root = nodeToDelete.right;
				nodeToDelete = null;
			}
		}
	}

	/*
		Notkun: twoLeafs(node);
		Fyrir: node er hlekkur sem � a� ey�a �r Splay tr�nu
			hlekkurinn node hefur tv� lauf
		Eftir: B�i� er a� ey�a hlekknum node �r Splay tr�nu 
        */
	private void twoLeafs(Node nodeToDelete)
	{
		Node succesor = treeMin(nodeToDelete.right);
		nodeToDelete.key = succesor.key;
	
		if(succesor.right != null){
		
			oneLeafs(succesor);
			return;
		}
		else{
	
			zeroLeafs(succesor);
			return;
		}
			
	}
	
	/*
		Notkun: treeMin(node);
		Fyrir: node er hlekkur � Splay tr�
		Eftir: B�i� er a� finna minnsta gildi� � undirtr� �ar sem node er r�t undirtr�s
        */
	private Node treeMin(Node node)
	{
		while(node.left != null)
			node = node.left;
		return node;
	}
	
	/*
		Notkun: treeMax(node);
		Fyrir: node er hlekkur � Splay tr�
		Eftir: B�i� er a� finna h�sta gildi� � undirtr� �ar sem node er r�t undirtr�s
        */
	private Node treeMax(Node node)
	{
		while(node.right != null)
			node = node.right;
		return node;
	}
	
	/*
		Notkun: Splay(node);
		Fyrir: node er hlekkur � Splay tr�
		Eftir: node er or�in r�t Splay tr�sins
	*/
	private void Splay(Node node)
	{
		if(node.parent == null)
			return;
		else if(node.parent.parent == null)
		{
			Node rootParent = node.parent;
			if(node == rootParent.left)
			{
					rotateRight(node);
					Splay(node);
			}
			else
			{
				rotateLeft(node);
				Splay(node);
			}
		}
		else
		{
			Node grandParent = node.parent.parent;
			Node parent = node.parent;
			if(parent == grandParent.left)
			{
				if(node == parent.left){
					// Sikk-sikk:
					//        g        n
					//       / \      / \
					//      p   D    A   p
					//     / \          / \
					//    n   C   =>   B   g
					//   / \              / \
					//  A   B            C   D
					rotateRight(parent);
					rotateRight(node);
					Splay(node);
				}
				else {
					// Sikk-sakk:
					//       g          n
					//      / \   =>   / \
					//     p   D      /   \
					//    / \        p     g
					//   A   n      / \   / \
					//      / \    A   B C   D
					//     B   C
					rotateLeft(node);
					rotateRight(node);
					Splay(node);
				}
			}
			else {
				if(node == parent.left){
					// Sakk-sikk:
					//        g           n
					//       / \   =>    / \
					//      A   p       /   \
					//         / \     g     p
					//        n   D   / \   / \
					//       / \     A   B C   D
					//      B   C
					rotateRight(node);
					rotateLeft(node);
					Splay(node);
				}
				else {
					// Sakk-sakk:
					//     g               n
					//    / \     =>      / \
					//   A   p           p   D 
					//      / \         / \
					//     B   n       g   C
					//        / \     / \
					//       C   D   A   B
					rotateLeft(parent);
					rotateLeft(node);
					Splay(node);
				}
			}
		}
	}
	
	/*
		Notkun: rotateLeft(node);
		Fyrir : node er hlekkur � Splay tr�
		Eftir : B�i� er a� f�ra node upp um eitt s�ti eins og s�st � eftirfarandi mynd
			(n = node)

		    p          n
		   / \   =>   / \
		  A   n      p   C
		     / \    / \
		    B   C  A   B
	*/
	private void rotateLeft(Node node)
	{
		Node parent = node.parent;
		if(parent != null){
			Node grandParent = node.parent.parent;
			if(grandParent != null){
				if(parent == grandParent.left){
					grandParent.left = node;
				}
				else{
					grandParent.right = node;
				}
				if(node.left != null){
					node.left.parent = parent;
				}
				parent.right = node.left;
				parent.parent = node;
				node.left = parent;
				node.parent = grandParent;
			}
			else{
				Node rootParent = parent.parent;
				if(node.left != null){
					node.left.parent = parent;
				}
				parent.right = node.left;
				parent.parent = node;
				node.left = parent;
				node.parent = rootParent;
			}
		}
	}

	/*
		Notkun: rotateRight(node);
		Fyrir : node er hlekkur � Splay tr�
		Eftir : B�i� er a� f�ra node upp um eitt s�ti eins og s�st � eftirfarandi mynd
			(n = node)

		      p        n
		     / \  =>  / \
		    n   C    A   p
		   / \          / \
		  A   B        B   C
		
	*/
	private void rotateRight(Node node)
	{
		Node parent = node.parent;
		if(parent != null){
			Node grandParent = node.parent.parent;
			if(grandParent != null){
				if(parent == grandParent.left){
					grandParent.left = node;
				}
				else{
					grandParent.right = node;
				}
				if(node.right != null){
					node.right.parent = parent;
				}
				parent.left = node.right;
				parent.parent = node;
				node.right = parent;
				node.parent = grandParent;
			}
			else{
				Node rootParent = parent.parent;
				if(node.right != null){
					node.right.parent = parent;
				}
				parent.left = node.right;
				parent.parent = node;
				node.right = parent;
				node.parent = rootParent;
			}
		}
	}
}