# card.rb
class Card

	attr_reader :suit, :value
	
	def initialize(suit, value)
		@suit = suit
		@value = value
	end
	
	def value
		return 10 if ["J", "K", "Q"].include?(@value)
		return 11 if @value == "A"
		return @value
	end
	
	def to_s
		"#{@value}-#{suit}"
	end
	
end

class Deck

	def self.build_cards
		cards = []
		[:clubs, :diamonds, :spades, :hearts].each do |suit|
			(2..10).each do |number|
				cards << Card.new(suit,number)
			end
			["J", "Q", "K", "A"].each do |facecard|
				cards << Card.new(suit, facecard)
			end
		end
		cards.shuffle
	end
	
end
