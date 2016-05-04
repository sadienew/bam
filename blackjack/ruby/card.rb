# card.rb
class Card

	attr_accessor :number, :suit, :value
	
	def intialize(number, suit, value)
		@number = number
		@suit = sut
		@value = value
	end
	
	def to_s
		"#{@number} of #{@suit}"
	end
	
end