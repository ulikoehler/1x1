class CreateMessages < ActiveRecord::Migration
  def self.up
    create_table :messages do |t|
      t.integer :id
      t.string :title
      t.string :body
      t.integer :viewsleft

      t.timestamps
    end
  end

  def self.down
    drop_table :messages
  end
end
