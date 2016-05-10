# blackjackMain.rb
choice = nil
@player = []
@dealer = []
@deck = []

#Card class
class Card
	#Member instances
	attr_accessor :suit, :face, :value
	
	def initialize(suit, face, value)
		@suit = suit
		@face = face
		@value = value
	end

	def getVal
		return @value
	end
	
	def to_s
		puts "#{@face} of #{suit}"
	end
end

class Deck
	attr_accessor :deck
	
	def initialize
		@cards = []
		@suits = ["clubs", "diamonds", "spades", "hearts"]
		@facecards = ["K", "Q", "J", "A"]
		
		@suits.each do |suit|
			(2..10).each do |number|
				val = value(number)
				@cards << Card.new(suit, number, val)
			end
	
			@facecards.each do |face|
				val = value(face)
				@cards << Card.new(suit, face, val)
			end
		end
	
		@deck = @cards.shuffle
		return @deck
	end
	
	#Draw card
	def hit
		return @deck.shift
	end
end

class Player
	attr_accessor :hand, :winnings

	def initialize
		@hand = []
		@winnings = 0
	end
	
	def getScore(hand)
		score = 0
		
		hand.each do |x|
			score += x.getVal
		end
	
		return score
	end
	
	def getWinnings
		return @winnings
	end
	
	def setWinnings(bet)
		@winnings = bet
	end
	
	#Show hand of player
	def turnPlayerHand(hand)
		puts "Player's hand: "
		
		hand.each do |x|
			x.to_s
		end
		
		puts "\n"
	end
end


class Dealer
	attr_accessor :hand, :winnings

	def initialize
		@hand = []
		@winnings = 0
	end
	
	def getWinnings
		return @winnings
	end
	
	def getScore(hand)
		score = 0
		
		hand.each do |x|
			score += x.getVal
		end
		
		return score
	end
	
	def setWinnings(bet)
		@winnings = bet
	end
	
	def turnDealerHand(hand)
		puts "Dealer's hand: "
		
		hand.each do |x|
			x.to_s
		end
		
		puts "\n"
	end
end

#Determine value of each card
def value(val)
	if val == "K"
		return 10
	elsif val == "Q"
		return 10
	elsif val == "J"
		return 10
	elsif val == "A"
		return 11
	else
		return val
	end
end

#Determine if hand is a blackjack
def isBlackjack(dealer, player, bet)
	
	if dealer.getScore(dealer.hand) == 21 && player.getScore(player.hand) == 21
		puts "Its a tie! You both got blackjack!"
		puts "Player's winnings: " + player.getWinnings.to_s
		puts "Dealer's winnings: " + dealer.getWinnings.to_s
		exit
	elsif dealer.getScore(dealer.hand) == 21
		puts "Blackjack! Dealer Wins!"
		dealer.setWinnings(bet)
		puts "Player's winnings: " + player.getWinnings.to_s
		puts "Dealer's winnings: " + dealer.getWinnings.to_s
		exit
	elsif player.getScore(player.hand) == 21
		puts "You've got a blackjack! Its the dealer's turn."
	else
		return
	end
		
end

def isPlayerBust(dealer, player, bet)
	if player.getScore(player.hand) > 21
		puts "You busted, scrub!"
		dealer.setWinnings(bet)
		puts "Player's winnings: " + player.getWinnings.to_s 
		puts "Dealer's winnings: " + dealer.getWinnings.to_s 
		exit
	else
		return
	end
end

def isDealerBust(dealer, player, bet)
	if dealer.getScore(dealer.hand) > 21
		player.setWinnings(bet)
		puts "Dealer busted, yo! You win!"
		puts "Player's winnings: " + player.getWinnings.to_s
		puts "Dealer's winnings: " + dealer.getWinnings.to_s
		exit
	else
		return
	end
end

def determineWinner(dealer, player, bet)
	if dealer.getScore(dealer.hand) == player.getScore(player.hand)
		puts "This a tie, yo!"
		puts "Player's winnings: " + player.getWinnings.to_s
		puts "Dealer's winnings: " + dealer.getWinnings.to_s
	elsif dealer.getScore(dealer.hand) < player.getScore(player.hand)
		puts "You Win!"
		player.setWinnings(bet)
		puts "Player's winnings: " + player.getWinnings.to_s
		puts "Dealer's winnings: " + dealer.getWinnings.to_s
	elsif dealer.getScore(dealer.hand) > player.getScore(player.hand)
		puts "You Lose..."
		puts "Player's winnings: " + player.getWinnings.to_s
		dealer.setWinnings(bet)
		puts "Dealer's winnings: " + dealer.getWinnings.to_s
	end
	
	return
end

#create deck
@deck = Deck.new
@player = Player.new
@dealer = Dealer.new
@bet = 0

puts "How much would you like to bet (50 - 500)?"
@bet = gets

#Initial hand of player and dealer
2.times{@player.hand << @deck.hit}
2.times{@dealer.hand << @deck.hit}

#Show player's hand
@player.turnPlayerHand(@player.hand)
puts "The player's score is: \n" + @player.getScore(@player.hand).to_s + "\n\n"

@dealer.turnDealerHand(@dealer.hand)
puts "The dealer's score is: \n" + @dealer.getScore(@dealer.hand).to_s + "\n\n"

isBlackjack(@dealer, @player, @bet) 

if @player.getScore(@player.hand) != 21
	puts "Would you like to hit or stand?"
	choice = gets

	#Keep hitting if player wants to
	while choice.chomp == "hit" do
		# << pushes onto a stack
		@player.hand << @deck.hit
		@player.turnPlayerHand(@player.hand)
		puts "The player's score is: \n" + @player.getScore(@player.hand).to_s + "\n\n"
		
		isPlayerBust(@dealer, @player, @bet)
		
		puts "\nWould you like to hit or stand?"
		choice = gets
	end
end

while @dealer.getScore(@dealer.hand) < 16
	@dealer.hand << @deck.hit
	@dealer.turnDealerHand(@dealer.hand)
	isDealerBust(@dealer, @player, @bet)
end

puts "\n"
@dealer.turnDealerHand(@dealer.hand)
puts "The dealer's score is: \n" + @dealer.getScore(@dealer.hand).to_s + "\n\n"

determineWinner(@dealer, @player, @bet)
