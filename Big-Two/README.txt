
=============================================================

				        Big 2 Poker Card Game
					      Ho Yat Aaron Ng
					  
=============================================================


Big 2 is a Chinese card game that is often played with a gambling rules or certain win conditions.
The game always has 4 players similar to Mah-Jong and each player will have 13 cards. The main
objective of the game is to get rid of your cards. The gambling aspect comes in when you lose,
depending on what type of cards you have left determines how much you pay.

This game has a variety of different rule sets, each province in China most likely has its own rule
set. The one coded is based on the rule set of Hong Kong and is the rule set that I play by.

The base model has currently been completed. The end goal will be to have three different bot
levels (easy, medium, hard), as well as a simple GUI.

======================================
How the game is played.
======================================

Card Order:
The value and the suit matter in this game, the lowest card is 3 of diamonds and the largest card is
2 of spades.

(Left is the lowest, Right is the highest)
The order goes: 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A, 2
The suite order goes: Diamond, Club, Heart, Spade

Formation of the game:
The game is played in rounds, once a player passes a round, he will not be able to play a card until
the round is over. The round ends when everyone else passes, the last man standing gets to choose
the starting round card or cards.

Round:
A round is set depending on the first card or cards played. 
A player can play:
	- singles 
	(a single card and the next person must play a higher single card)

	- pairs 
	(a pair of cards is two cards with the same value, the next person must play a pair higher then
	the previous played pair)

	- triples 
	(similar to pairs but instead of two cards with the same value, there is now three cards of the
	same value)

	- poker hands (Sets of 5)
	(A poker hand can be though as, straight, flush, full house, quads, straight flush, royal flush.
	Each consecutive player must play a higher hand, same rules to poker apply.)

Starting Player:
The starting player is determined by the person holding the 3 of diamonds. He can play any type of
hand (singles, pairs, triples, poker hands) as long as the 3 of diamonds is played.

Payout rules:
Not implemented yet. The rules will come when implemented. 

