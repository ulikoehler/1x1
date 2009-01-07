class AddUidToMessages < ActiveRecord::Migration
  def self.up
    add_column :messages, :uid, :integer
  end

  def self.down
    remove_column :messages, :uid
  end
end
