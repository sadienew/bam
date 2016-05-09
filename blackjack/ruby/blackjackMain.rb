# blackjackMain.rb
cards = []
suits = ["clubs", "diamonds", "spades", "hearts"]
facecards = ["K", "Q", "J", "A"]
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

#Draw card
def hit
	return @deck.shift
end

#Determine if hand is a blackjack
def isBlackjack(hand)
	total = 0
	
	hand.each do |x|
		total = total + x.getVal
	end
	
	if total == 21
		return true
	else
		return false
	end
		
end

#Show hand of player
def turnPlayerHand(hand)
	score = 0
	puts "Players hand: "
	
	hand.each do |x|
		x.to_s
		score += x.getVal
	end
	
	puts "Players current score: "
	puts score
end




#Build unshuffled deck of cards
suits.each do |suit|
	(2..10).each do |number|
		val = value(number)
		cards << Card.new(suit, number, val)
	end
	
	facecards.each do |face|
		val = value(face)
		cards << Card.new(suit, face, val)
	end
end

#shuffle deck
@deck = cards.shuffle

#Initial hand of player and dealer
2.times{@player << hit}
2.times{@dealer << hit}

#Show player's hand
turnPlayerHand(@player)

puts "Would you like to hit or stand?"
choice = gets

#Keep hitting if player wants to
while choice.chomp == "hit" do
	# << pushes onto a stack
	@player << hit
	turnPlayerHand(@player)
	
	puts "Would you like to hit?"
	gets(choice)
end