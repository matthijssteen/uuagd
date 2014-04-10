

-- UUAGC 0.9.50.2 (/tmp/uuagd1672053706394119174.ag)

{-# LINE 1 "Example5.ag" #-}

import Text.Blaze.Html5 (toHtml)
import UUAGD.Html.Renderer
{-# LINE 10 "Example5.hs" #-}

{-# LINE 53 "Example5.ag" #-}

import qualified UUAGD.CodeGen as UUAGD
import UUAGD.Html.CodeGen
{-# LINE 16 "Example5.hs" #-}
{-# LINE 40 "Example5.ag" #-}

main :: IO ()
main = do
  result <- render $ uuagd_Syn_Root (wrap_Root test Inh_Root)
  putStrLn result

testRoot :: Root
testRoot = Root (Node (Tip 1) (Node (Tip 2) (Tip 3)))

test :: T_Root
test = sem_Root testRoot
{-# LINE 29 "Example5.hs" #-}
-- Root --------------------------------------------------------
data Root = Root (Tree)
          deriving ( Show)
-- cata
sem_Root :: Root ->
            T_Root
sem_Root (Root _tree) =
    (sem_Root_Root (sem_Tree _tree))
-- semantic domain
type T_Root = ( Root,Html)
data Inh_Root = Inh_Root {}
data Syn_Root = Syn_Root {transformed_Syn_Root :: Root,uuagd_Syn_Root :: Html}
wrap_Root :: T_Root ->
             Inh_Root ->
             Syn_Root
wrap_Root sem (Inh_Root) =
    (let ( _lhsOtransformed,_lhsOuuagd) = sem
     in  (Syn_Root _lhsOtransformed _lhsOuuagd))
sem_Root_Root :: T_Tree ->
                 T_Root
sem_Root_Root tree_ =
    (let _treeOreplacement :: Int
         _lhsOuuagd :: Html
         _lhsOtransformed :: Root
         _treeIsum :: Int
         _treeItransformed :: Tree
         _treeIuuagd :: Html
         _treeOreplacement =
             ({-# LINE 37 "Example5.ag" #-}
              _tree'replacement
              {-# LINE 60 "Example5.hs" #-}
              )
         _tree'replacement =
             ({-# LINE 38 "Example5.ag" #-}
              _treeIsum
              {-# LINE 65 "Example5.hs" #-}
              )
         _lhsOuuagd =
             ({-# LINE 61 "Example5.ag" #-}
              UUAGD.markupAlt "Root" "Root" [UUAGD.markupTerminal "tree" _treeIuuagd, UUAGD.markupAttrUsage "tree" "replacement" ((toHtml . show) (_tree'replacement     :: Int))]
              {-# LINE 70 "Example5.hs" #-}
              )
         _transformed =
             ({-# LINE 19 "Example5.ag" #-}
              Root _treeItransformed
              {-# LINE 75 "Example5.hs" #-}
              )
         _lhsOtransformed =
             ({-# LINE 19 "Example5.ag" #-}
              _transformed
              {-# LINE 80 "Example5.hs" #-}
              )
         ( _treeIsum,_treeItransformed,_treeIuuagd) =
             tree_ _treeOreplacement
     in  ( _lhsOtransformed,_lhsOuuagd))
-- Tree --------------------------------------------------------
data Tree = Node (Tree) (Tree)
          | Tip (Int)
          deriving ( Show)
-- cata
sem_Tree :: Tree ->
            T_Tree
sem_Tree (Node _left _right) =
    (sem_Tree_Node (sem_Tree _left) (sem_Tree _right))
sem_Tree (Tip _value) =
    (sem_Tree_Tip _value)
-- semantic domain
type T_Tree = Int ->
              ( Int,Tree,Html)
data Inh_Tree = Inh_Tree {replacement_Inh_Tree :: Int}
data Syn_Tree = Syn_Tree {sum_Syn_Tree :: Int,transformed_Syn_Tree :: Tree,uuagd_Syn_Tree :: Html}
wrap_Tree :: T_Tree ->
             Inh_Tree ->
             Syn_Tree
wrap_Tree sem (Inh_Tree _lhsIreplacement) =
    (let ( _lhsOsum,_lhsOtransformed,_lhsOuuagd) = sem _lhsIreplacement
     in  (Syn_Tree _lhsOsum _lhsOtransformed _lhsOuuagd))
sem_Tree_Node :: T_Tree ->
                 T_Tree ->
                 T_Tree
sem_Tree_Node left_ right_ =
    (\ _lhsIreplacement ->
         (let _lhsOsum :: Int
              _lhsOuuagd :: Html
              _lhsOtransformed :: Tree
              _leftOreplacement :: Int
              _rightOreplacement :: Int
              _leftIsum :: Int
              _leftItransformed :: Tree
              _leftIuuagd :: Html
              _rightIsum :: Int
              _rightItransformed :: Tree
              _rightIuuagd :: Html
              _lhsOsum =
                  ({-# LINE 32 "Example5.ag" #-}
                   _lhs'sum
                   {-# LINE 126 "Example5.hs" #-}
                   )
              _lhs'sum =
                  ({-# LINE 33 "Example5.ag" #-}
                   _leftIsum + _rightIsum
                   {-# LINE 131 "Example5.hs" #-}
                   )
              _lhsOuuagd =
                  ({-# LINE 64 "Example5.ag" #-}
                   UUAGD.markupAlt "Tree" "Node" [UUAGD.markupTerminal "left" _leftIuuagd, UUAGD.markupTerminal "right" _rightIuuagd, UUAGD.markupAttrUsage "lhs" "sum" ((toHtml . show) (_lhs'sum     :: Int))]
                   {-# LINE 136 "Example5.hs" #-}
                   )
              _transformed =
                  ({-# LINE 19 "Example5.ag" #-}
                   Node _leftItransformed _rightItransformed
                   {-# LINE 141 "Example5.hs" #-}
                   )
              _lhsOtransformed =
                  ({-# LINE 19 "Example5.ag" #-}
                   _transformed
                   {-# LINE 146 "Example5.hs" #-}
                   )
              _leftOreplacement =
                  ({-# LINE 22 "Example5.ag" #-}
                   _lhsIreplacement
                   {-# LINE 151 "Example5.hs" #-}
                   )
              _rightOreplacement =
                  ({-# LINE 22 "Example5.ag" #-}
                   _lhsIreplacement
                   {-# LINE 156 "Example5.hs" #-}
                   )
              ( _leftIsum,_leftItransformed,_leftIuuagd) =
                  left_ _leftOreplacement
              ( _rightIsum,_rightItransformed,_rightIuuagd) =
                  right_ _rightOreplacement
          in  ( _lhsOsum,_lhsOtransformed,_lhsOuuagd)))
sem_Tree_Tip :: Int ->
                T_Tree
sem_Tree_Tip value_ =
    (\ _lhsIreplacement ->
         (let _lhsOtransformed :: Tree
              _lhsOsum :: Int
              _lhsOuuagd :: Html
              _lhsOtransformed =
                  ({-# LINE 27 "Example5.ag" #-}
                   _lhs'transformed
                   {-# LINE 173 "Example5.hs" #-}
                   )
              _lhs'transformed =
                  ({-# LINE 28 "Example5.ag" #-}
                   Tip _lhsIreplacement
                   {-# LINE 178 "Example5.hs" #-}
                   )
              _lhsOsum =
                  ({-# LINE 29 "Example5.ag" #-}
                   _lhs'sum
                   {-# LINE 183 "Example5.hs" #-}
                   )
              _lhs'sum =
                  ({-# LINE 30 "Example5.ag" #-}
                   value_
                   {-# LINE 188 "Example5.hs" #-}
                   )
              _lhsOuuagd =
                  ({-# LINE 65 "Example5.ag" #-}
                   UUAGD.markupAlt "Tree" "Tip" [UUAGD.markupNonTerminal "value" ((toHtml . show) value_), UUAGD.markupAttrUsage "lhs" "transformed" ((toHtml . show) (_lhs'transformed     :: Tree)), UUAGD.markupAttrUsage "lhs" "sum" ((toHtml . show) (_lhs'sum     :: Int))]
                   {-# LINE 193 "Example5.hs" #-}
                   )
              _transformed =
                  ({-# LINE 19 "Example5.ag" #-}
                   Tip value_
                   {-# LINE 198 "Example5.hs" #-}
                   )
          in  ( _lhsOsum,_lhsOtransformed,_lhsOuuagd)))
