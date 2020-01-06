<?php namespace App\Database\Migrations;

use ICI4\Helpers\Database\Table\Field;
use ICI4\Migration;

class CreatTableBarang extends Migration
{
	public function up()
	{
		$this->forge->addField(array_merge(
			Field::autoIncrement(),
			Field::string('nama'),
			Field::float('harga'),
			Field::CUDTimestamps()
		))->addPrimaryKey('id')->createTable('barang');
	}

	//--------------------------------------------------------------------

	public function down()
	{
		$this->forge->dropTable('barang', true);
	}
}
